// UserOrders.js
import React, { useEffect, useState } from "react";
import axios from "axios";

const UserOrders = ({ user }) => {
  const [orders, setOrders] = useState([]);
  const [phoneNumber, setPhoneNumber] = useState(user.phone);
  const [editingPhoneNumber, setEditingPhoneNumber] = useState(false);

  useEffect(() => {
    // Fetch orders for the logged-in user
    console.log(user);
    const fetchOrders = async () => {
      try {
        // Make a GET request to fetch orders for the authenticated user from the backend
        const response = await axios.get(
          `http://localhost:8082/order/user?id=${user.userId}`
        );
        setOrders(response.data);
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    };

    fetchOrders();
  }, [user]);

  const handleAddOrder = async () => {
    try {
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, "0");
      const day = String(today.getDate()).padStart(2, "0");

      const formattedDate = `${year}-${month}-${day}`;
      const newOrder = {
        userId: user.userId,
        datePicked: formattedDate,
        orderId: "1",
      };

      const response = await axios.post(
        "http://localhost:8082/order",
        newOrder
      );

      setOrders([...orders, response.data]);
    } catch (error) {
      console.error("Error adding order:", error);
    }
  };

  const handleEditPhoneNumber = async () => {
    try {
      await axios.put(`http://localhost:8082/user`, {
        ...user,
        phone: phoneNumber,
      });
      setEditingPhoneNumber(false); // Toggle editing mode off
    } catch (error) {
      console.error("Error updating phone number:", error);
    }
  };

  const deleteAccount = async () => {
    try {
      await axios.delete(`http://localhost:8082/user?id=${user.userId}`);
      window.location.reload(true);
    } catch (error) {
      console.error("Error deleting account:", error);
    }
  };

  const logOut = () => {
    window.location.reload(true);
  };

  return (
    <div>
      <h2>User Orders</h2>
      <button onClick={handleAddOrder}>Add Order</button>
      <button onClick={logOut}>Logout</button>
      <button onClick={deleteAccount}>Delete Account</button>
      <div>
        {editingPhoneNumber ? (
          <div>
            <input
              type="text"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
            />
            <button onClick={handleEditPhoneNumber}>Save</button>
          </div>
        ) : (
          <div>
            <input type="text" value={phoneNumber} readOnly />
            <button onClick={() => setEditingPhoneNumber(true)}>
              Edit Phone Number
            </button>
          </div>
        )}
      </div>
      <ul className="list-group">
        {orders.map((order) => (
          <li key={order.orderId} className="list-group-item">
            <div className="d-flex w-100 justify-content-between">
              <h5 className="mb-1">Order ID: {order.orderId}</h5>
              <small>{order.datePicked}</small>
            </div>
            <p className="mb-1">User ID: {order.userId}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default UserOrders;
