/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.packtpub.as7development.chapter7.producer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

 

import com.packtpub.as7development.chapter7.model.SeatType;
import com.packtpub.as7development.chapter7.repository.DataManager;

@RequestScoped
public class SeatTypeProducer implements Serializable {

    @Inject
    private DataManager seatRepository;

    private List<SeatType> seatTypes;
    


    @Produces
    @Named
    public List<SeatType> getSeatTypes() {
        return seatTypes;
    }

    public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SeatType member) {
        retrieveAllSeats();
    }

    @PostConstruct
    public void retrieveAllSeats() {
    	seatTypes = seatRepository.findAllSeatTypes();
    }
}
