import React, { Component } from "react";

import UserService from "../services/user.service";
import AuthService from "../services/auth.service";

export default class BoardUserComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            content: "",
            iscrizioni: []
        };
    }

    componentDidMount() {
        const currentUser = AuthService.getCurrentUser();

        const clienteId = currentUser.id;

        UserService.getTesseraByClienteId(clienteId).then(
            response => {
                this.setState({
                    tessera: response.data
                });
            },
            error => {
                console.error("Errore nel recupero della tessera:", error);
            }
        );

    }

    render() {
        const { tessera } = this.state;

        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>Tessera PF</h3>
                </header>
                {tessera && (
                    <div className="tessera">
                        <p>Numero Tessera: {tessera.tesseraId}</p>
                        <p>Iscrizioni Tessera: {tessera.iscrizioni}</p>
                        {/* Aggiungi altri dettagli della tessera se necessario */}
                    </div>
                )}
            </div>
        );
    }
}