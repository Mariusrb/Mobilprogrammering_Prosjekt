package no.hiof.mariusrb.minkokebok.Directions_and_Args

import android.os.Bundle
import androidx.navigation.NavArgs

data class RecipeDetailFragmentArgs(val uid: Int) : NavArgs {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt("uid", this.uid)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): RecipeDetailFragmentArgs {
            bundle.setClassLoader(RecipeDetailFragmentArgs::class.java.classLoader)
            val __uid : Int
            if (bundle.containsKey("uid")) {
                __uid = bundle.getInt("uid")
            } else {
                throw IllegalArgumentException("Required argument \"uid\" is missing and does not have an android:defaultValue")
            }
            return RecipeDetailFragmentArgs(__uid)
        }
    }
}