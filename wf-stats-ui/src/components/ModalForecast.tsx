import React from "react";
import {Box, Button, Icon, Modal, Typography} from "@mui/material";

interface IModal {
    open: boolean
}

export default function ModalForecast(props: IModal) {
    const [open, setOpen] = React.useState(props.open);
    const handleClose = () => setOpen(!open);

    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description">
            <Box className={"box"}>
                <Typography id="modal-modal-title" variant="h6" component="h2">
                    <Button className="modal-close-button" onClick={handleClose}>
                        <Icon>
                            <img className="close-image" src="/images/close-icon.svg" alt={'close-icon'}/>
                        </Icon>
                    </Button>
                    Forecast
                </Typography>
                <Typography id="modal-modal-description" sx={{mt: 2}}>

                </Typography>
            </Box>
        </Modal>
    );
};