package com.eventoapp.eventoapp.controller;

import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

        @Autowired
        private EventoRepository er;

        @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
        public String form(){

            return "evento/formEvento";
        }

        @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
        public String form(Evento evento) {
            er.save(evento);

            return "redirect:/cadastrarEvento";
        }

        @RequestMapping("/eventos")
        public ModelAndView listaEventos(){
            ModelAndView mv = new ModelAndView("index");
            Iterable<Evento> eventos = er.findAll();
            mv.addObject("eventos", eventos);

            return mv;
        }

    @RequestMapping(value = "/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        Evento evento = er.findByCodigo(codigo );
        mv.addObject("evento", evento);

        return mv;
    }
}
