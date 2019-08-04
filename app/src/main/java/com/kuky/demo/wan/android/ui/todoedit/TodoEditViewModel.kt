package com.kuky.demo.wan.android.ui.todoedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuky.demo.wan.android.base.CODE_SUCCEED
import com.kuky.demo.wan.android.base.safeLaunch
import java.util.*

/**
 * @author kuky.
 * @description
 */
class TodoEditViewModel(private val repository: TodoEditRepository) : ViewModel() {

    val todoType = MutableLiveData<Int>()

    val todoPriority = MutableLiveData<Int>()

    val todoDate = MutableLiveData<String>()

    init {
        val calendar = Calendar.getInstance()

        todoType.value = 0
        todoPriority.value = 0
        todoDate.value = TodoEditFragment.formatDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun addTodo(param: HashMap<String, Any>, success: () -> Unit, fail: (String) -> Unit) {
        viewModelScope.safeLaunch {
            repository.addTodo(param).let {
                if (it.code == CODE_SUCCEED) success() else fail(it.message)
            }
        }
    }

    fun updateTodo(id: Int, param: HashMap<String, Any>, success: () -> Unit, fail: (String) -> Unit) {
        viewModelScope.safeLaunch {
            repository.updateTodo(id, param).let {
                if (it.code == CODE_SUCCEED) success() else fail(it.message)
            }
        }
    }

    fun deleteTodo(id: Int, success: () -> Unit, fail: (String) -> Unit) {
        viewModelScope.safeLaunch {
            repository.deleteTodo(id).let {
                if (it.code == CODE_SUCCEED) success() else fail(it.message)
            }
        }
    }
}