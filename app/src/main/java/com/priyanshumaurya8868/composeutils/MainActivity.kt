package com.priyanshumaurya8868.composeutils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.priyanshumaurya8868.composeutils.ui.theme.ComposeUtilsTheme
import java.util.*


class MainActivity : ComponentActivity() {
    @ExperimentalMotionApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUtilsTheme {
                Column {
                    
                    var prog  by remember {
                        mutableStateOf(0f)
                    }
                    ProfileHeader(progress = prog)
                    Spacer(modifier = Modifier.height(32.dp))
                    Slider(
                        value = prog,
                        onValueChange = {
                            prog = it
                        },
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }

            }
        }
    }


}

@ExperimentalMotionApi
@Composable
fun ProfileHeader(progress: Float) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_screen)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val properties = motionProperties(id = "profile_pic")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .layoutId("box")
        )
        Image(
            painter = painterResource(id = R.drawable.my_pic),
            contentDescription = "Profile pic",
            modifier = Modifier
                .layoutId("profile_pic")
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = properties.value.color("background"),
                    shape = CircleShape
                )
        )
        Text(
            text="Priyanshu Maurya",
            fontSize = 24.sp,
            modifier =  Modifier.layoutId("username"),
            color = properties.value.color("background")
        )

    }
}


