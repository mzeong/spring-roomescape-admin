package roomescape.dto;

import java.util.List;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

public record ReservationResponse(long id, String name, String date, ReservationTime time) {

    public static ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime()
        );
    }

    public static List<ReservationResponse> toResponses(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationResponse::toResponse)
                .toList();
    }
}
