package net.fonix232.phoneme.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


@Serializable
data object PhoneList

@Serializable
data class PhoneDetail(val phoneId: Int)

@Serializable
data object TodoList

@Serializable
data class TodoDetail(val todoId: String? = null)


enum class AppDestinations(
    @Serializable val route: Any,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
) {
    PhoneListDest(PhoneList, "Phones", Icons.Outlined.Phone, Icons.Filled.Phone),
    TodoListDest(TodoList, "Todos", Icons.Outlined.CheckCircle, Icons.Filled.CheckCircle)
}
