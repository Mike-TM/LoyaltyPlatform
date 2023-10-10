import React, { Component }  from 'react';
import PFService from "../services/PFService";


class ListaPFComponent extends Component {

    constructor(props) {
        super(props);

        this.state ={
            programmi: []
        }
    }

    componentDidMount(){
       PFService.getProgrammi().then((response) => {
            this.setState({ programmi: response.data})
        });
    }

    render() {
        return (
                <div>
                    <h1 className = "text-center"> Lista programmi fedeltà</h1>
                    <table className = "table table-striped">
                        <thead>
                        <tr>
                            <td> Id programma</td>
                            <td> Nome</td>
                        </tr>

                        </thead>
                        <tbody>
                        {
                            this.state.programmi.map(
                                programma =>
                                    <tr key = {programma.programmaId}>
                                        <td> {programma.programmaId}</td>
                                        <td> {programma.nome}</td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
        );
    }
}

export default ListaPFComponent;