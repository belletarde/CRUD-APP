package projects.kevin.crudapplication.repository

import androidx.lifecycle.LiveData
import projects.kevin.crudapplication.data.db.dao.SubscribeDAO
import projects.kevin.crudapplication.data.db.entity.SubscriberEntity

class DatabaseDataSource(
    private val subscriberDAO: SubscribeDAO
) : SubscriberRepository {
    override suspend fun insertSubscriber(name: String, email: String): Long {

        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )

        return subscriberDAO.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )

        subscriberDAO.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDAO.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDAO.deleteAll()
    }

    override suspend fun getAllSubscriber(): List<SubscriberEntity> {
        return subscriberDAO.getAll()
    }

}