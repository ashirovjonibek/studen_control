import React, {Component} from 'react';
import '../../../style/modal.css'
import $ from 'jquery'
import axios from "axios";
import {BASE_URL} from "../../const/constants";

class LocationModal extends Component {
    constructor(props) {
        super(props);
        this.state={
            countries:[],
            regions:[],
            districts:[]
        }
    }

    componentDidMount(){
         axios.get(BASE_URL+"address/getAllCountry").then((response)=>{
            this.setState({
                countries: JSON.parse(JSON.stringify(response.data.object))
            })
        });

        axios.get(BASE_URL+"address/getAllRegion").then((response)=>{
            this.setState({
                regions: JSON.parse(JSON.stringify(response.data.object))
            })
        });

        axios.get(BASE_URL+"address/getAllDistrict").then((response)=>{
            this.setState({
                districts: JSON.parse(JSON.stringify(response.data.object))
            })
        });
    }

    updateAll(){
        axios.get(BASE_URL+"address/getAllCountry").then((response)=>{
            this.setState({
                countries: JSON.parse(JSON.stringify(response.data.object))
            })
        });

        axios.get(BASE_URL+"address/getAllRegion").then((response)=>{
            this.setState({
                regions: JSON.parse(JSON.stringify(response.data.object))
            })
        });

        axios.get(BASE_URL+"address/getAllDistrict").then((response)=>{
            this.setState({
                districts: JSON.parse(JSON.stringify(response.data.object))
            })
        });
    }

    addCountry=()=>{
        if ($("#inputNameCountry").val()&&$("#inputZipCountry").val()){
            let country={
            id:null,
            name:$("#inputNameCountry").val(),
            code:$("#inputZipCountry").val()
        };
            axios.post(BASE_URL+"address/saveOrEditCountry",country).then((response)=>{
                country=JSON.parse(JSON.stringify(response.data.object));
                console.log(country)
            });

            $("#inputNameCountry").css("border-color","rgb(206, 212, 218)");
            $("#inputZipCountry").css("border-color","rgb(206, 212, 218)");
            $("#inputNameCountry").val("");
            $("#inputZipCountry").val("");
            this.updateAll();
        }else {

            $("#inputNameCountry").css("border-color","red");
            $("#inputZipCountry").css("border-color","red");
        }
    };

    addRegion=()=>{
        if($("#inputNameRegion").val()&&$("#countryNames").val()){
            let region={
                id:null,
                name:$("#inputNameRegion").val(),
                countryId:$("#countryNames").val()
            };
            axios.post(BASE_URL+"address/saveOrEditRegion",region).then((response)=>{
                region=response.data.object;
            });
            $("#inputNameRegion").css("border-color","rgb(206, 212, 218)");
            $("#countryNames").css("border-color","rgb(206, 212, 218)");
            $("#inputNameRegion").val("");
            $("#countryNames").val("");
            this.updateAll()
        }else {
            $("#inputNameRegion").css("border-color","red");
            $("#countryNames").css("border-color","red");
        }
    };

    addDistrict=()=>{
        if ($("#inputNameDistrict").val()&&$("#regionNames").val()){
            let district={
                id:null,
                name:$("#inputNameDistrict").val(),
                regionId:$("#regionNames").val()
            };
            axios.post(BASE_URL+"address/saveOrEditDistrict",district).then((response)=>{
                district=response.data.object;
            });
            $("#inputNameDistrict").css("border-color","rgb(206, 212, 218)");
            $("#regionNames").css("border-color","rgb(206, 212, 218)");
            $("#inputNameDistrict").val("");
            $("#regionNames").val("");
            this.updateAll();
        }else {
            $("#inputNameDistrict").css("border-color","red");
            $("#regionNames").css("border-color","red");
        }
    };

    toggleCountry=()=>{
        $("#addcouuntrybody").toggle();
        $(".country-list").toggle();
    };
    toggleRegion=()=>{
        $("#regiontoggle").toggle();
        $(".region-list").toggle();
    };

    toggleDistrict=()=>{
        $("#districttoogle").toggle();
        $(".district-list").toggle();
    };

    style={
        cursor:"pointer"
    };

    render() {
        console.log(this.state.countries);
        return (
            <div>
                <div className="modal fade bd-example-modal-lg " tabIndex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div className="modal-dialog modal-lg">
                        <div className="modal-content">
                            <div className="modal-header">Manzillar qo'shish</div>
                            <div className="modal-body">
                                <div className="country-form" id="countryForm"><b style={this.style} onClick={this.toggleCountry}>Mamlakat qo'shish</b>
                                    <div className="row" id="addcouuntrybody">
                                        <div className="form-group col-md-6">
                                            <label htmlFor="inputNameCountry">Mamlakat nomi</label>
                                            <input type="text" className="form-control" id="inputNameCountry"
                                                   placeholder="Nomi"/>
                                        </div>
                                        <div className="form-group col-md-5">
                                            <label htmlFor="inputZipCountry">Pochta indexi</label>
                                            <input type="text" className="form-control" id="inputZipCountry"
                                                   placeholder="Pocta indeksi"/>
                                        </div>
                                        <div className="form-group col-md-1">
                                            <button className="btn btn-success" style={{marginTop:"30px"}} onClick={this.addCountry}><i className="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div className="country-list">
                                    <b>Mamlakatlar</b>
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nomi</th>
                                            <th>Pochta indexsi</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        {this.state.countries.map((item)=>
                                            <tr >
                                                <td>{item.id}</td>
                                                <td>{item.name}</td>
                                                <td>{item.code}</td>
                                            </tr>
                                        )}
                                        </tbody>
                                    </table>
                                </div>
                                <hr/>
                                <div className="add-region"><b onClick={this.toggleRegion} style={this.style}>Viloyt qo'shish:</b>
                                    <div className="row" id="regiontoggle"><input type="hidden" value="1" id="countryId"/>
                                        <div className="form-group col-md-6">
                                            <label htmlFor="countryNames">Viloyat nomi</label>
                                            <select id="countryNames" className="form-control" >
                                                <option></option>
                                                {this.state.countries.map((item)=>
                                                    <option value={item.id}>{item.name}</option>
                                                )}
                                            </select>
                                        </div>
                                        <div className="form-group col-md-5">
                                            <label htmlFor="inputNameRegion">Viloyat nomi</label>
                                            <input type="text"  className="form-control" id="inputNameRegion"
                                                   placeholder="Nomi"/>
                                        </div>
                                        <div className="form-group col-md-1">
                                            <button className="btn btn-success" defaultValue="1" disabled={!this.state.countries.length} onClick={this.addRegion} style={{marginTop:"30px"}}><i className="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div className="region-list"><b>Viloyatlar:</b>
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Viloyat nomi</th>
                                            <th>Mamlakat nomi</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        {this.state.regions.map((item)=>
                                            <tr>
                                                <td>{item.id}</td>
                                                <td>{item.name}</td>
                                                <td>{item.country.name}
                                                </td>
                                            </tr>
                                        )}
                                        </tbody>
                                    </table>
                                </div>
                                <hr/>
                                <div className="add-district"><b onClick={this.toggleDistrict} style={this.style}>Tuman qo'shish</b>
                                    <div className="row" id="districttoogle">
                                        <div className="form-group col-md-6">
                                            <label htmlFor="regionNames">Viloyat nomi</label>
                                            <select id="regionNames" className="form-control" >
                                                <option></option>
                                                {this.state.regions.map((item)=>
                                                    <option value={item.id}>{item.name}</option>
                                                )}
                                            </select>
                                        </div>
                                        <div className="form-group col-md-5">
                                            <label htmlFor="inputNameDistrict">Tuman nomi</label>
                                            <input type="text"  className="form-control" id="inputNameDistrict"
                                                   placeholder="Nomi"/>
                                        </div>
                                        <div className="form-group col-md-1">
                                            <button className="btn btn-success" disabled={this.state.regions.length===0} defaultValue="1"  onClick={this.addDistrict} style={{marginTop:"30px"}}><i className="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div className="district-list"><b>Tumanlar</b>
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Tuman nomi</th>
                                            <th>Viloyat nomi</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        {this.state.districts.map((item)=>
                                            <tr>
                                                <td>{item.id}</td>
                                                <td>{item.name}</td>
                                                <td>{item.region.name}</td>
                                            </tr>
                                        )}
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div className="modal-footer">
                                <button type="button" onClick={() => window.location.reload(false)} className="btn btn-primary" data-toggle="modal"
                                        data-target=".bd-example-modal-lg">
                                   Yopish
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }


}

export default LocationModal;