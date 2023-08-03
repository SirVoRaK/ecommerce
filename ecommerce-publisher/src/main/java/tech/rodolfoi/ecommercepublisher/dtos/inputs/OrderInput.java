package tech.rodolfoi.ecommercepublisher.dtos.inputs;

import java.util.List;

public record OrderInput(String order, String origin, List<ItemInput> items) {}
