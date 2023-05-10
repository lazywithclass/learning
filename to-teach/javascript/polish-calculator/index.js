const pop = s => s.pop()
const put = (s, e) => s.push(e)

class PolishNotation {

  constructor() {
    this.stack = []
  }

  // 1 2 + 3 *
  // T F or not
  eval(e) {
    // prendo il prossimo token
    // ho visto altri token prima di questo?
    //      si: e' dello stesso tipo? se no errore
    //      no: mi salvo il tipo
    //
    // il token attuale e' un operando?
    //      mettilo sullo stack
    // il token attuale e' un operatore?
    //       per ogni argomento richiesto dall'operatore
    //              pulla dallo stack
    //       infine applica l'operatore
    //       e metti il risultato nello stack
    // salta il token e vai al prossimo

    let token = e.slice(1, e.length)
    if (this.isOperand(token)) {
      put(this.stack, token)
    }
    if (this.isOperator(token)) {
      // per ogni etc
    }

    this.eval(e)


  }

  isOperator(token) {
    return ['+', '-', '*', '**', '/', 'and', 'or', 'not'].indexOf(token) > - 1
  }

  isOperand(token) {
    return !isNaN(token) || token == 'T' || token == 'F'
  }

  toString() {

  }
}

pn = new PolishNotation()
pn.eval('1 2 + 3 *')
