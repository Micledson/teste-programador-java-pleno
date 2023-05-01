import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useForm } from "react-hook-form";
import {API} from "../../../utils/api";
import {useParams, useNavigate} from 'react-router-dom';
import {BackButton} from "../../BackButton";
import {IProduct} from "../interfaces/interfaces";

export function Update() {
    const { register, handleSubmit} = useForm();

    const navigate = useNavigate();

    const {id} = useParams();

    function submit(product: any) {
        API.put<IProduct>(`product/${id}`, product).then(data => {
            alert("Product successfully updated")
            navigate("/product");
        }).catch(err => {
            alert(`Error: ${err.response.data}`)
        });
    }

    return (
        <Form onSubmit={handleSubmit(submit)}>
            <Form.Group className="mb-3" controlId="formDescription">
                <Form.Label>Description</Form.Label>
                <Form.Control type="text" placeholder="Enter description"
                              {...register("description")}  />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formUnity">
                <Form.Label>Unities</Form.Label>
                <Form.Control type="number" placeholder="Enter unity" min={1}
                              {...register("unity")}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPrice">
                <Form.Label>Price</Form.Label>
                <Form.Control type="number" placeholder="Enter price" min="0.00" max="10000.00" step="0.01"
                              {...register("price")}/>
            </Form.Group>

            <Button variant="primary" type="submit">
                Submit
            </Button>

            <BackButton/>
        </Form>
    );
}