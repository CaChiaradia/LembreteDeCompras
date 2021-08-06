package br.com.carolchiaradia.lembretedecompras.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.carolchiaradia.lembretedecompras.models.Produto

//Camada de Banco de Dados ela ultiliza na Dao (define quais são as entidades no banco de dados, qual a versão do database)

@Database(entities = [Produto::class], version = 1, exportSchema = false)
public abstract class LembretedeComprasRoomDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LembretedeComprasRoomDatabase? = null

        fun getDatabase(context: Context): LembretedeComprasRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LembretedeComprasRoomDatabase::class.java,
                    "lembrete_compra_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}