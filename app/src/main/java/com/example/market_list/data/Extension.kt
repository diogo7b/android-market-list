import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.market_list.data.AppDatabase

val Context.db: AppDatabase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "marketList.db"
    ).build()

private fun migrate(version: IntRange, sql: () -> String) =
    object : Migration(version.first, version.last) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(sql())
        }
    }