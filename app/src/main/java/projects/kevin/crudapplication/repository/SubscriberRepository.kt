package projects.kevin.crudapplication.repository

import androidx.lifecycle.LiveData
import projects.kevin.crudapplication.data.db.entity.SubscriberEntity

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    suspend fun getAllSubscriber(): List<SubscriberEntity>
}