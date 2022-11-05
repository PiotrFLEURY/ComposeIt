package fr.piotrfleury.composeit.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.piotrfleury.composeit.domain.entities.User
import fr.piotrfleury.composeit.presentation.ui.theme.ComposeItTheme
import fr.piotrfleury.composeit.presentation.viewmodels.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: UserListViewModel by viewModel()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Button(onClick = { viewModel.getUsers() }) {
                            Text(text = "Get users")
                        }
                        UserList(viewModel.userList)
                    }
                }
            }
        }
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            UserItem(user = user)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    ComposeItTheme {
        UserList(
            listOf(
                aTypicUser(),
                aTypicUser(),
                aTypicUser(),
            )
        )
    }
}

@Composable
fun UserItem(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
        AsyncImage(
            model = user.picture,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "${user.firstName} ${user.lastName}", fontSize = 24.sp)
            Text(text = user.phone, fontSize = 12.sp, color = Color.Gray)
            Text(text = user.email, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeItTheme {
        UserItem(
            user = aTypicUser(),
        )
    }
}

fun aTypicUser() = User(
    firstName = "Piotr",
    lastName = "Fleury",
    email = "piotr.fleury@gmail.com",
    phone = "06 12 34 56 78",
    picture = "https://randomuser.me/api/portraits/med/men/75.jpg"
)