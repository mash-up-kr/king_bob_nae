package com.example.king_bob_nae.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewDataBindingDelegate<T : ViewDataBinding>(
    bindingClass: Class<T>,
    private val activity: ComponentActivity
) : ReadOnlyProperty<ComponentActivity, T> {

    private var binding: T? = null
    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        activity.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    activity.lifecycleScope.launch(Dispatchers.Main) {
                        binding?.unbind()
                        binding = null
                    }
                }
            }
        })
    }

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): T {
        if (thisRef.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED).not()) {
            error("Cannot access view bindings. Activity lifecycle is ${thisRef.lifecycle.currentState}!")
        }

        binding?.let { return it }
        val root = thisRef.window?.decorView?.findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0)
        binding = (bindMethod.invoke(null, root) as T).also { binding ->
            binding.lifecycleOwner = thisRef
        }

        return binding!!
    }
}

class FragmentViewDataBindingDelegate<T : ViewDataBinding>(
    bindingClass: Class<T>,
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        fragment.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    fragment.lifecycleScope.launch(Dispatchers.Main) {
                        binding?.unbind()
                        binding = null
                    }
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        // onCreateView may be called between onDestroyView and next Main thread cycle.
        // In this case [binding] refers to the previous fragment view. Check that binding's root view matches current fragment view
        if (binding != null && binding?.root !== thisRef.view) {
            binding = null
        }
        binding?.let { return it }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }

        @Suppress("UNCHECKED_CAST")
        binding = bindMethod.invoke(null, thisRef.requireView()) as T
        return binding!!
    }
}

inline fun <reified T : ViewDataBinding> ComponentActivity.viewDataBinding() =
    ActivityViewDataBindingDelegate(T::class.java, this)

inline fun <reified T : ViewDataBinding> Fragment.viewDataBinding() = FragmentViewDataBindingDelegate(T::class.java, this)
