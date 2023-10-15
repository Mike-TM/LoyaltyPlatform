
import AuthService from "../services/auth.service";
import React from 'react';
import UserService from '../services/user.service';

export default class UserComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            content:"",
            iscrizioni: []
        }
    }

    componentDidMount(){
        const cliente=AuthService.getCurrentUser();
        const idCliente=cliente.id;
        const tessera=UserService.getTesseraByClienteId(idCliente);
        UserService.getTesseraByClienteId(idCliente).then((response) => {
            this.setState({ tessera: response.data})
        });

    }

    render (){
        const { tessera } = this.state;
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>Tessera PF</h3>
                </header>
                {tessera && (
                    <div className="tessera">
                        <p>Numero Tessera: {tessera.tesseraId}</p>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Programma fedeltà</th>
                                </tr>
                            </thead>
                        <tbody>
                    {this.state.tessera.iscrizioni.map((subscription) => (
                        <tr key={subscription.id}>
                            <td>{subscription.id}</td>
                            <td>{subscription.programma}</td>
                        </tr>
                        ))}
                        </tbody>
                        </table>
                    </div>
                )}
            </div>
        )
    }
}
//
