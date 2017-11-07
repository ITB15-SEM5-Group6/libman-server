package at.fhv.itb.sem5.team6.libman.server.application;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * supports up- and down-casts between model hierarchies
 * <p>
 * EXAMPLE USAGE:
 * // data
 * ImmutableMedia i = new Media();
 * MutableMedia m = new Media();
 * Media o = new Media();
 * <p>
 * // immutable conversions
 * Media oi = CastDown(i);
 * MutableMedia mi = CastDown(i);
 * <p>
 * // mutable conversions
 * Media om = CastDown(m);
 * ImmutableMedia im = CastUp(m);
 * <p>
 * // model conversions
 * ImmutableMedia io = CastUp(o);
 * MutableMedia mo = CastUp(o);
 */
public interface Convertible {
    default <A extends B, B> B castUp(@NotNull A item) {
        return item;
    }

    default <A extends B, B> List<B> castUp(@NotNull List<A> items) {
        return new ArrayList<>(items);
    }

    default <A extends B, B> A castDown(@NotNull B item) {
        return (A) item;
    }

    default <A extends B, B> List<A> castDown(@NotNull List<B> items) {
        return items.stream().map(x -> (A) x).collect(Collectors.toList());
    }
}
