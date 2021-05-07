import React, {Component} from 'react';
import {EXPIRE_TIME, LOGIN_TIME, ROLE, TOKEN} from "./const/constants";
import AdminPage from "./page/AdminPage";
import jwtDecode from "jwt-decode";
import ErrorPage from "./componet/ErrorPage";
import DeanPage from "./page/DeanPage";
import DeputyDeanPage from "./page/DeputyDeanPage";
import TeacherPage from "./page/TeacherPage";
import StudentPage from "./page/StudentPage";

class Home extends Component {
    constructor(props) {
        super(props);
        this.state={
            content:false,
            role:localStorage.getItem(ROLE)
        }
    }
    componentDidMount() {
        if (localStorage.getItem(ROLE) === null||
            (new Date(JSON.parse(localStorage.getItem(LOGIN_TIME),10)).getTime())
            <(new Date().getTime())-EXPIRE_TIME){
           window.location.href="/login";
        }

        let d=jwtDecode(localStorage.getItem(TOKEN));
        console.log(d);
        console.log(new Date(JSON.parse(localStorage.getItem(LOGIN_TIME),10)),new Date().getTime()-EXPIRE_TIME)


    }

    render() {
        return (
            <div>
                {this.state.role==="ROLE_ADMIN"
                &&this.props.sect==="ra"?<AdminPage/>:""}
                {this.state.role==="ROLE_DEAN"
                &&this.props.sect==="rd"?<DeanPage/>:""}
                {this.state.role==="ROLE_DEPUTY_DEAN"
                &&this.props.sect==="rdd"?<DeputyDeanPage/>:""}
                {this.state.role==="ROLE_TEACHER"
                &&this.props.sect==="rt"?<TeacherPage/>:""}
                {this.state.role==="ROLE_STUDENT"
                &&this.props.sect==="rs"?<StudentPage/>:""}

            </div>
        );
    }
}

export default Home;