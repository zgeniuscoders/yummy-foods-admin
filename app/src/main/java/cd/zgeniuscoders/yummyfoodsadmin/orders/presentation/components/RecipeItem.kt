package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Recipe
import coil3.compose.AsyncImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeItem(
    data: Recipe,
    onDelete: (id: String) -> Unit,
    onUpdate: (id: String) -> Unit,
) {

    Card{

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(80.dp)
            ) {
                AsyncImage(
                    model = data.recipePhoto,
                    contentDescription = "image de la recette ${data.name}",
                    contentScale = ContentScale.Crop
                )
            }

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(data.name, style = MaterialTheme.typography.titleMedium)
                    Text("${data.price}")
                }

                Row {
                    IconButton(
                        onClick = { onDelete(data.id) }
                    ) {
                        Icon(
                            Icons.Rounded.Edit,
                            contentDescription = "modification de la recette ${data.name}"
                        )
                    }

                    IconButton(
                        onClick = { onUpdate(data.id) }
                    ) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "supression de la recette ${data.name}"
                        )
                    }
                }
            }

        }


    }

}