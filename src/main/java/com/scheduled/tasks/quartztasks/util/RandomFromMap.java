package com.scheduled.tasks.quartztasks.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RandomFromMap<A extends Map> {

    public String getSomeKey(A myMap)
    {
        List<String> keys = new ArrayList<>( myMap.keySet() );
        Collections.shuffle(keys);
        return keys.get(0);
    }

}
