import React, {useEffect, useState} from "react";
import Table from 'react-bootstrap/Table';
import {API} from "../../../utils/api";
import Dropdown from 'react-bootstrap/Dropdown';
import {DropdownButton} from "react-bootstrap";
import {useNavigate, useParams} from 'react-router-dom';
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {ICart, ICreateOrder, IProduct} from "../interfaces/interfaces";

export function List() {

    const [products, setProducts] = useState<IProduct[]>([]);
    const [carts, setCarts] = useState<IProduct[]>([]);
    const [description, setDescription] = useState<string>("");


    const navigate = useNavigate();

    const {userId} = useParams();

    useEffect(() => {
        API.get<IProduct[]>("/product").then(({data}) =>
            setProducts(data))
    }, [])

    function deleteProduct(product: IProduct) {
        API.delete(`/product/${product.id}`).then(data => {
            alert("Product has been deleted");
            setProducts(products.filter(obj => obj.id !== product.id));
        }).catch(err => {
            alert(`Err: ${err.response.data}`)
        })
    }

    function addToCart(product: IProduct, quantity: any) {
        carts.map(cart => {
            if (cart.id === product.id) cart.quantity = quantity
        })

        const items = carts.filter(obj => obj.id === product.id)
        if (items.length === 0) setCarts([...carts, product])
        console.log(carts)
    }

     function purchase() {
        const order: ICart = {
            clientId: userId!!,
            products: carts
        }

        const createOrder: ICreateOrder = {
            description: description,
            clientId: userId!!,
        }


        API.post('/order', createOrder).then(({data}) => {
            API.post(`/order/${data.id}/product`, carts).then(value => {
                alert("Purchase made successfully");
                console.log(value.data)
            }).catch(err => {
                alert(`Err: ${err.response.data}`)
            })
        }).catch(err => {
            alert(`Err: ${err.response.data}`)
        })

    }


    function buyOptions(userId: any, product: IProduct) {
        if (userId !== undefined) {
            return (
                <>
                    <td><Form.Control onChange={e => addToCart(product, e.target.value)} type="number"
                                      placeholder="Enter quantity" required={false} min="0"
                                      max={product.unities} step="1"/></td>

                    <td><Form.Control onChange={e => setDescription(e.target.value)} type="text"
                                      placeholder="Enter description"/></td>
                </>
            )
        }
    }

    return (
        <>
            <Table striped size="sm">
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Unities</th>
                    <th>Price (R$)</th>
                    {
                        userId !== undefined ? <th>Quantity</th> : <></>
                    }
                    {
                        userId !== undefined ? <th>Description</th> : <></>
                    }
                    <th>Settings</th>
                </tr>
                </thead>
                <tbody>
                {products.map(product => {
                    return (
                        <tr>
                            <td>{product.description}</td>
                            <td>{product.unities}</td>
                            <td>{product.price}</td>
                            {
                                buyOptions(userId, product)
                            }
                            <td><DropdownButton title="More" id="bg-nested-dropdown">
                                <Dropdown.Item eventKey="1"
                                               onClick={e => navigate(`/product/update/${product.id}`)}>Update</Dropdown.Item>
                                <Dropdown.Item eventKey="2" onClick={e => deleteProduct(product)}>Delete</Dropdown.Item>
                            </DropdownButton></td>

                        </tr>
                    );
                })}
                </tbody>
            </Table>
            {
                userId !== undefined ?
                    <Button variant="primary" onClick={purchase}>
                        Buy
                    </Button> : <></>
            }
        </>
    );
}