import React, {Component} from 'react';

import NavbarComponent from "./components/Navbar";
import LoginPage from "./components/LoginPage";
import 'bootstrap/dist/css/bootstrap.min.css'
import { BrowserRouter,
    Switch,
    Route,
    Link} from "react-router-dom";
import {Redirect} from 'react-router'
import Home from "./components/Home";
import PublicRoute from "./components/utills/PublicRoute";
import {TOKEN} from "./components/const/constants";
// import 'bootstrap/dist/js/bootstrap.min'

class App extends Component{


    render(){
      return (
          <div>
              <BrowserRouter>
                  <Switch>
                      <Route exact path="/" component={Home}/>
                      <PublicRoute exact path="/login" component={LoginPage}/>
                  </Switch>
              </BrowserRouter>
          </div>
      )
  };
}

export default App;
