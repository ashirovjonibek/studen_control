import React, {Component} from 'react';
import {Spinner} from "../componet/Spinner";
import NavbarComponent from "../componet/Navbar";

class DeputyDeanPage extends Component {
    constructor(props) {
        super(props);
        this.state={
            teachers:[],
            students:[],
            passport:[],
            reference:[],
            relations:[],
            districts:[],
            reports:[],
            groups:[],
            directions:[],
            isLoading:true
        }
    }

    componentDidMount() {
        let a=true;
        setTimeout(()=>{
            this.setState({
                isLoading: false
            })
        }, 3000);

    }

    render() {
        return (
            <div>
                {this.state.isLoading?<Spinner/>:<div>
                    <NavbarComponent/>
                    Deputy dean page


                </div>}
            </div>
        );
    }
}

export default DeputyDeanPage;