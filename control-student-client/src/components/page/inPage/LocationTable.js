import React, {Component} from 'react';
import axios from 'axios';
import LocationModal from "../../componet/modals/LocationModal";
import {BASE_URL} from "../../const/constants";

class LocationTable extends Component {
    constructor(props) {
        super(props);
        this.state={
            districts:[]
        }
    }

    componentDidMount() {

        axios.get(BASE_URL+"address/getAllDistrict").then((response)=>{
            this.setState({
                districts:response.data.object
            })
        });


    }

    render() {
        console.log(this.state)
        return (
            <div>
                <div className="container">
                    <LocationModal/>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-primary" data-toggle="modal"
                                                          data-target=".bd-example-modal-lg">Manzil qo'shish
                        </button>
                    </div>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Mamlakat nomi</th>
                            <th>Viloyat nomi</th>
                            <th>Tuman nomi</th>
                            <th>Pochta indexi</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                           this.state.districts.map((item)=>
                               <tr key={item.id}>
                                   <td>{item.id}</td>
                                   <td>{item.region.country.name}</td>
                                   <td>{item.region.name}</td>
                                   <td>{item.name}</td>
                                   <td>{item.region.country.code}</td>
                               </tr>
                           )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default LocationTable;