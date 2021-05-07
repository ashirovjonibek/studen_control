import React, {Component} from 'react';
import axios from 'axios';
import EducationModal from "../../componet/modals/EducationModal";
import {BASE_URL} from "../../const/constants";
import {Spinner} from "../../componet/Spinner";

class EducationTable extends Component {
    constructor(props) {
        super(props);
        this.state={
            educations:[],
            isLoading:false
        }
    }

    componentDidMount() {
        this.setState({
            isLoading:true
        });
        axios.get(BASE_URL+"education/getAllEducation").then((response)=>{
            console.log(response.data);
            this.setState({
                educations:response.data.object,
                isLoading:false
            })
        })
    }

    updateAll=()=>{
        this.setState({
            isLoading:true
        });
        axios.get(BASE_URL+"education/getAllEducation").then((response)=>{
            console.log(response.data);
            this.setState({
                educations:response.data.object,
                isLoading:false
            })
        })
    };

    render() {
        return (
            <div>
                <EducationModal/>
                <h2 className="align-content-center">Ta'lim muassasalari</h2>
                <div className="modal-footer">
                    <button className="btn btn-success" onClick={this.updateAll} >
                        <i className="fas fa-cloud-download-alt"></i>
                    </button>
                    <button type="button" className="btn btn-primary" data-toggle="modal"
                            data-target=".bd-example-modal-lg">Ta'lim muassasasi qo'shish
                    </button>
                </div>
                <table className="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Muassasa nomi</th>
                        <th>Manzili</th>
                    </tr>
                    </thead>
                    {
                        this.state.isLoading?<Spinner/>:
                            <tbody>
                            {
                                this.state.educations.map((item)=>
                                    <tr>
                                        <td>{item.id}</td>
                                        <td>{item.name}</td>
                                        <td>{item.address.district.region.country.name+", "+
                                        item.address.district.region.name+", "+
                                        item.address.district.name+", "+
                                        item.address.address
                                        }</td>
                                    </tr>
                                )
                            }
                            </tbody>
                    }
                </table>
            </div>
        );
    }
}

export default EducationTable;