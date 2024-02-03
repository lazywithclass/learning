import { elementoCasuale, scacchiera, stampaScacchiera } from "./Scacchi.js"
import {
  mosseLegaliRegina,
  mosseLegaliTorre,
  mosseLegaliAlfiere,
} from "./pedine.js"
import { mossa } from "./giocatore.js"

stampaScacchiera()
for (let i = 0; i < 10; i++) {
  if (i % 2 == 0) {
    mossa("b")
  } else {
    mossa("n")
  }
  stampaScacchiera()
}
