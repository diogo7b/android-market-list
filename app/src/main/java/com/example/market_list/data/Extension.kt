import android.content.Context
import androidx.room.Room
import com.example.market_list.data.AppDatabase

val Context.db: AppDatabase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "marketList.db"
    ).build()