
import AuthService from "../services/auth.service";
import React from 'react';
import UserService from '../services/user.service';
import {render} from "@testing-library/react";
import axios from "axios";

export default class UserComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            iscrizioni: []
        };
    }


    componentDidMount() {
        const cliente = AuthService.getCurrentUser();
        const idCliente = cliente.id;
        const tessera = UserService.getTesseraByClienteId(idCliente);
        const idTessera = tessera.id;
        fetch("api/tessera/cliente/"+idCliente)
    .then((res) => res.json())
           .then((data) => {
                console.log(data);
                this.setState({iscrizioni: JSON.stringify(data)}, () => {
                    alert(this.state.hugeText);
                });

            });
    }

    render() {
            return (
                <div>
                    <h2>Iscrizioni:</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Id iscrizione</th>
                        </tr>
                        </thead>
                        <tbody>
                            {this.state.data?.map((iscrizione) => (
                                <tr key={iscrizione.iscrizioneId}>
                                    <td>{iscrizione.iscrizioneId}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            );
    }
}