package com.lazywithclass.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// preimage, image
class Relation<P, I> {

    // Represents a binary relationship between a set S and a set T
    // It accepts duplicates

    private Map<P, Set<I>> toImage;
    private Map<I, Set<P>> toPreImage;

    public Relation() {
        toImage = new HashMap<>();
        toPreImage = new HashMap<>();
    }

    // adds a couple of objects to the relation
    public void put(P p, I i) {
        Set<I> images = toImage.get(p);
        if (images == null) {
            images = new HashSet<>();
            images.add(i);
            toImage.put(p, images);
        } else {
            images.add(i);
        }

        Set<P> preImages = toPreImage.get(i);
        if (preImages == null) {
            preImages = new HashSet<>();
            preImages.add(p);
            toPreImage.put(i, preImages);
        } else {
            preImages.add(p);
        }
    }

    // removes a couple of objects from the relation
    public void remove(P p, I i) {
        Set<I> images = toImage.get(p);
        if (images.contains(i)) {
            images.remove(i);
            if (images.isEmpty()) {
                toImage.remove(p);
            }
        }

        Set<P> preImages = toPreImage.get(i);
        if (preImages.contains(p)) {
            preImages.remove(p);
            if (preImages.isEmpty()) {
                toPreImage.remove(i);
            }
        }
    }

    public Set<I> image(P p) {
        return toImage.get(p);
    }

    public Set<P> preImage(I i) {
        return toPreImage.get(i);
    }
}
