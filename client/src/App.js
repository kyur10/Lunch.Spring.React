import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import DataList from "./DataList";
import SignIn from "./sign";
import { useState } from "react";
import UserOrders from "./order";
import Registration from "./register";

function App() {
  const [user, setUser] = useState(null);
  return (
    <>
      <div className="container mt-5">
        <div className="row">
          <div className="col-md-6 offset-md-3">
            {!user ? (
              <Registration setUser={setUser} />
            ) : (
              <UserOrders user={user} />
            )}
            {!user && <SignIn setUser={setUser} />}
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
/* 
1--
<BrowserRouter>
  <Routes>
    <Route path="/" element={<SignIn />}></Route>
    <Route path="/" element={<DataList />}></Route> 
  </Routes>
</BrowserRouter>

2--
    <Router>
      <Switch>
        <Route path="/signin" component={SignIn} />
        <Route path="/userorders" component={UserOrders} />
        <Route path="/" component={SignIn} />
      </Switch>
    </Router>
     */
