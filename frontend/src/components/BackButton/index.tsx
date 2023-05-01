import React from "react";
import Button from "react-bootstrap/Button";
import {useNavigate} from 'react-router-dom';

export function BackButton() {
    const navigate = useNavigate();
    return (
        <Button variant="danger" type="button" onClick={e => navigate(-1)}>
            Back
        </Button>
    )
}
