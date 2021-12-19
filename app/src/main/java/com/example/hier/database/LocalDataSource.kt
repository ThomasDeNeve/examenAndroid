package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hier.models.Reservation
import com.example.hier.models.Room
import com.example.hier.network.LoginResponse
import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.ReservationNetworkModel

class LocalDataSource(
    private val locationDao: LocationDao,
    private val userDao: UserDao,
    private val reservationDao: ReservationDao,
    private val roomDao: RoomDao
) {
    // fun getLocations() = locationDao.getAllLocations()
    fun getRooms() = roomDao.getRooms()
    // fun getRoomsByNeededSeats(neededseats:Int) = roomDao.getRoomsByNeededSeats(neededseats)

    fun getRoomById(roomId: Int) = roomDao.getRoom(roomId)
    fun getLocationById(locationId: Int) = roomDao.getLocation(locationId)
    fun getLocationIdByName(name: String) = locationDao.getLocationIdByName(name)

    fun getReservations() = reservationDao.getAllReservations()

    fun getUser(username: String): LiveData<LoginResponse> {
        return MutableLiveData(LoginResponse(false, "", userDao.getUser(username)))
    }

    fun saveUser(loginResponse: LoginResponse) {
        userDao.insert(loginResponse.user)

        // TODO: post request to api to add user in our own database
    }

    fun saveReservations(list: List<ReservationNetworkModel>) {
        val reservationList = ArrayList<Reservation>()
        list.forEach { res -> reservationList.add(res.toDataBaseModel()) }
        reservationDao.insertAll(reservationList)
    }

    /*fun saveLocations(list: List<LocationNetworkModel>)
    {
        val locations = ArrayList<Location>()
        val rooms = ArrayList<Room>()

        val lwrList: List<LocationWithRooms> = list.map { item -> item.toDatabaseModel() }
        for (lwr in lwrList) {
            locations.add(lwr.location)
            rooms.addAll(lwr.rooms)
        }
        locationDao.insertAll(locations)
        roomDao.insertAll(rooms)
    }*/

    /*
    * Yves
    * Takes a list of MeetingRoomNetworkModel objects (received through the API)
    * and maps it to a Room object (to be saved in the local database)
    * */
    fun saveRooms(list: List<MeetingRoomNetworkModel>) {
        val rooms = ArrayList<Room>()

        val mappedList: List<Room> = list.map { item -> item.toDatabaseModel() }

        rooms.addAll(mappedList)

        // Delete all rooms in the cached database Before filling it with data retreived from the api. This ensures we have the updated data
        roomDao.deleteAllRooms()
        roomDao.insertAll(rooms)
    }
}
