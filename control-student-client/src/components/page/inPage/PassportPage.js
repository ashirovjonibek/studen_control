import React, {Component} from 'react';
import $ from 'jquery'

class PassportPage extends Component {
    render() {
        return (
            <div>
                <div style={{backgroundColor: "#e7e7e7"}}
                     className="row shadow mt-2 border container text-center d-flex justify-content-center">

                    <div className="col-md-3">
                        <img src="" alt="" id="imgForPassport" width="100%" className="col-md-3"/>
                        <label htmlFor="file" className=""></label>
                        <input type="file" id="file" name="file" className="form-group input-group"
                               onChange={(e) =>{
                                   let data=new FormData()
                                   data.append("file",e.target.files[0]);
                                   $("#imgForPassport").attr("src", data)
                                   console.log(URL.createObjectURL(e.target.files[0]))
                               }}
                        />
                    </div>

                    <input id="lastname" name="lastname" className="input-group form-control mt-2 col-md-3"
                           placeholder="Familiya"/>
                        <input name="firstname" className="input-group form-control mt-2 col-md-3" placeholder="Ism"/>
                            <input name="fatherName" className="input-group form-control mt-2 col-md-3"
                                   placeholder="Otasining ismi"/>
                                <input name="born_place" className="input-group form-control mt-2 col-6"
                                       placeholder="Tug'ilgan joyi"/>
                                    <input name="nationality" className="input-group form-control mt-2 col-4"
                                           placeholder="Millati"/>
                                        <input name="birthdate" type="date" placeholder="Tug'ilgan yili"
                                               className="input-group col-8 ml-2 mt-2 mt-2 form-control"/>
                                            <input name="" placeholder="Seriya"
                                                   className="input-group col-4 mt-2 form-control"/>
                                                <input name="" placeholder="Raqam"
                                                       className="input-group col-6 mt-2 form-control"/>
                                                    <input name="" type="date" placeholder="Berilgan sana"
                                                           className="input-group col-5 mt-2 form-control"/>
                                                        <input name="" type="date" placeholder="Amal qilish muddati"
                                                               className="input-group col-5 mt-2 form-control"/>

                                                            <button className="col-4 mt-2 mb-5 btn btn-success">Add
                                                            </button>

                </div>
            </div>
        );
    }
}

export default PassportPage;