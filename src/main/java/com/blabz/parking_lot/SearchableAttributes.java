package com.blabz.parking_lot;

import java.util.function.Predicate;

public interface SearchableAttributes {
    Predicate getFilter();
}
