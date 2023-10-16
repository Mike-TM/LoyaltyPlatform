import React from 'react';
import axios from "axios";
import authHeader from "../services/auth-header";
import "./listaIscrizioni.css"
import authService from "../services/auth.service";

export default class UserComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            iscrizioniUtente: [], // Cambiato da programmiFedelta a iscrizioniUtente
            disiscritto: false
        };
    }

    componentDidMount() {
        axios.get(`/api/tessera/${1}/iscrizioni`, { headers: authHeader() })
            .then(response => {
                this.setState({ iscrizioniUtente: response.data });
            })
            .catch(error => {
                console.error('Errore durante il recupero delle iscrizioni:', error);
            });
    }

    render() {
        const { iscrizioniUtente } = this.state;

        if (!iscrizioniUtente) {
            return null;
        }

        return (
            <div className="container">
                <h2 className="text-center mb-2 pb-2">Le Tue Iscrizioni</h2>
                <div className="programmi-container">
                    {iscrizioniUtente.map(iscrizione => (
                        <div key={iscrizione.iscrizioneId} className="iscrizione">
                            <div className="info">
                                <div className="info-label">Azienda:</div>
                                <div className="info-value">{iscrizione.programma.azienda.nome}</div>
                            </div>
                            <div className="info">
                                <div className="info-label">Nome Programma:</div>
                                <div className="info-value">{iscrizione.programma.nome}</div>
                            </div>
                            <div className="info">
                                <div className="info-label">Livello Attuale:</div>
                                <div className="info-value">{iscrizione.livelloCorrente.nome}</div>
                            </div>
                            <div className="info">
                                <div className="info-label">Progresso:</div>
                                <div className="info-value">{iscrizione.progressoLivello + "/" + iscrizione.livelloCorrente.expLevelUp}</div>
                            </div>
                            <div className="button-col">
                                <button
                                    className={`button-iscrizione${iscrizione.disiscritto ? '-disabled' : '-enabled'}`}
                                    onClick={() => this.handleDisiscriviti(iscrizione.iscrizioneId)}
                                    disabled={iscrizione.disiscritto}
                                >
                                    {iscrizione.disiscritto ? 'Disiscritto' : 'Disiscriviti'}
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        );
    }

    handleDisiscriviti = (iscrizioneId) => {
        const currentUser = authService.getCurrentUser();

            axios.delete(`/api/iscrizione/${iscrizioneId}`,{ headers: authHeader() })
                .then(response => {
                    // Aggiorna lo stato solo del programma che è stato iscritto
                    this.setState(prevState => ({
                        iscrizioniUtente: prevState.iscrizioniUtente.map(iscrizione => {
                            if (iscrizione.iscrizioneId === iscrizioneId) {
                                return {
                                    ...iscrizione,
                                    disiscritto: true
                                };
                            }
                            return iscrizione;
                        })
                    }));
                })
                .catch(error => console.error('Errore durante l\'iscrizione:', error));
        }
}