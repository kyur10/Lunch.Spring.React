// SignIn.js
import React, { useState } from "react";
import axios from "axios";

const SignIn = ({ setUser }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setError] = useState("");

  const handleSignIn = async () => {
    try {
      const response = await axios.post("http://localhost:8082/user/login", {
        name: username,
        password,
      });
      setUser(response.data);
    } catch (error) {
      let status = error.response.status;
      if (status === 500) setError("Wrong credentials");
      else console.log(error.response.status);
      console.error("Error signing in:", error);
    }
  };

  return (
    <div className="card">
      <div className="card-body">
        <h2 className="card-title">Sign In</h2>
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
        <p className="text-danger mb-3">{errorMessage}</p>
        <button className="btn btn-primary" onClick={handleSignIn}>
          Sign In
        </button>
      </div>
    </div>
  );
};

export default SignIn;
