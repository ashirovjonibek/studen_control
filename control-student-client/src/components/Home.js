import React, {Component} from 'react';
import {Redirect} from 'react'
import NavbarComponent from "./Navbar";
import {TOKEN} from "./const/constants";

class Home extends Component {
    constructor(props) {
        super(props);
        this.state={
            content:false
        }
    }
    componentDidMount() {
        if (localStorage.getItem(TOKEN)===null){
            window.location.href="/login"
        }
    }

    render() {
        return (
            <div>
                <NavbarComponent/>
                Home Page
            </div>
        );
    }
}

export default Home;