package beri.android.supportModules.DataBase

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MigrationClass = object: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Subcategory ADD COLUMN chosen BOOLEAN default 'FALSE' NOT NULL")
    }
}