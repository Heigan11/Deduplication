import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container, Paper, Button } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));


export default function Client() {
  const paperStyle = { padding: '50px 30px', width: 500, margin: '30px auto' }
  const [fullName, setFullName] = useState('');
  const [passport, setPassport] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [formValid, setFormValid] = useState(false);

  const classes = useStyles();

  useEffect(() => {
    if (fullName != '' && passport != '' && birthDate != '')
      setFormValid(true)
    else
      setFormValid(false)
  }, [fullName, passport, birthDate])

  const handleClick = (e) => {
    e.preventDefault();
    const client = { fullName, passport, birthDate };
    if (fullName == '' || passport == '' || birthDate == '')
      return;
    fetch("http://localhost:5005/send",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(client)
      }
    );
    setFullName('');
    setPassport('');
    setBirthDate('');
  }

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <form className={classes.root} noValidate autoComplete="off">
          <TextField
            id="outlined-basic" label="ФИО" variant="outlined" fullWidth
            value={fullName}
            onChange={(e) => setFullName(e.target.value)}
          />
          <TextField id="outlined-basic" label="ДУЛ" variant="outlined" fullWidth
            value={passport}
            onChange={(e) => setPassport(e.target.value)}
          />
          <TextField id="date" label="ДР" variant="outlined" fullWidth
            type="date"
            value={birthDate}
            onChange={(e) => setBirthDate(e.target.value)}
            InputLabelProps={{
              shrink: true,
            }}
          />
          <Button disabled={!formValid} variant="contained" color="primary" onClick={handleClick}>
            Отправить
          </Button>
        </form>
      </Paper>
    </Container>
  );
}
