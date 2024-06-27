// Registration.js
import React, { useState } from "react";
import axios from "axios";

const Registration = ({ setUser }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [phone, setPhone] = useState("");

  const handleRegister = async () => {
    try {
      const response = await axios.post("http://localhost:8082/user", {
        userId: "1",
        name: username,
        password: password,
        remaining: 30,
        phone,
      });
      setUser(response.data);
    } catch (error) {
      console.error("Error registering:", error);
    }
  };

  return (
    <div className="card">
      <div className="card-body">
        <h2 className="card-title">Register</h2>
        <input
          type="text"
          className="form-control mb-3"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          className="form-control mb-3"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          type="phone"
          className="form-control mb-3"
          placeholder="Phone"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />
        <button className="btn btn-primary" onClick={handleRegister}>
          Register
        </button>
      </div>
    </div>
  );
};

export default Registration;
