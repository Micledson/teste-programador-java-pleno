import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputMask from "react-input-mask";
import {useForm} from "react-hook-form";
import {API} from "../../../utils/api";
import {useNavigate} from 'react-router-dom';
import {BackButton} from "../../BackButton";
import {isValidCPF, isValidPhone} from "../../../utils/clientValidate";
import {IClient} from "../interfaces/interfaces";

export function Create() {

    const {register, handleSubmit} = useForm();
    const navigate = useNavigate();

    function submit(client: any) {
        if (!isValidCPF(client.cpf)) {
            alert("Invalid CPF")
            return;
        }

        if (!isValidPhone(client.phone)) {
            alert("Invalid phone")
            return;
        }

        API.post<IClient>("/client", client).then(() => {
            alert("Client successfully created")
            navigate("/");
        }).catch(err => {
            alert(`Error: ${err.response.data}`)
        });
    }


    return (
        <Form onSubmit={handleSubmit(submit)}>
            <Form.Group className="mb-3" controlId="formName">
                <Form.Label>Name</Form.Label>
                <Form.Control type="name" placeholder="Enter name" required={true}
                              {...register("name")}  />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control type="Email" placeholder="Enter Email" required={true}
                              {...register("email")}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formCPF">
                <Form.Label>CPF</Form.Label>
                <Form.Control type="text" placeholder="Enter CPF" required={true}
                              {...register("cpf")} as={InputMask} mask="999.999.999-99"/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPhone">
                <Form.Label>Phone</Form.Label>
                <Form.Control type="name" placeholder="Enter Phone" required={true}
                              {...register("phone")} as={InputMask} mask="(99) 99999-9999" minLength={15}/>
            </Form.Group>
            <Button variant="primary" type="submit">
                Submit
            </Button>
           <BackButton/>
        </Form>
    );
}

