import React, {Component} from 'react';

import NavbarComponent from "./components/componet/Navbar";
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
import {Spinner} from "./components/componet/Spinner";
import DeanPage from "./components/page/DeanPage";
import AdminPage from "./components/page/AdminPage";
import DeputyDeanPage from "./components/page/DeputyDeanPage";
import StudentPage from "./components/page/StudentPage";
import TeacherPage from "./components/page/TeacherPage";
import LocationModal from "./components/componet/modals/LocationModal";
// import 'bootstrap/dist/js/bootstrap.min'

class App extends Component{


    render(){
      return (
          <div>
              {/*<BrowserRouter>*/}
              {/*    <Switch>*/}
              {/*        <Route exact path="/" component={()=><Home sect="ra"/>}/>*/}
              {/*        <Route exact path="/dean" component={()=><Home sect="rd"/>}/>*/}
              {/*        <Route exact path="/deputyDean" component={()=><Home sect="rdd"/>}/>*/}
              {/*        <Route exact path="/teacher" component={()=><Home sect="rt"/>}/>*/}
              {/*        <Route exact path="/student" component={()=><Home sect="rs"/>}/>*/}
              {/*        <PublicRoute exact path="/login" component={LoginPage}/>*/}
              {/*    </Switch>*/}
              {/*</BrowserRouter>*/}

              <AdminPage/>

          </div>
      )
  };
}

export default App;
