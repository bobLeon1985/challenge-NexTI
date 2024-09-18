/**
 * 
 */
package com.bce.cuentas.application.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author edwinleon
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoCuentasServicioImpl {/*implements IMovimientoCuentasServicio {

	@Value("${limite.retiro}")
	private BigDecimal LIMITE_DIARIO_RETIRO;
	@Autowired
	private ICuentaServicio cuentaServicio;

	@Autowired
	private IMovimientoServicio movimientoServicio;

	@Transactional
	@Override
	public synchronized Movimiento validaRegistroMovimiento(MovimientoDeCuentasDto movimientoDeCuentasDto)
			  {

		Movimiento movimiento = new Movimiento();
		movimiento.setFecha(LocalDateTime.now());
		movimiento.setNumeroCuenta(movimientoDeCuentasDto.getNumeroCuenta());
		movimiento.setTipoMovimiento(movimientoDeCuentasDto.getTipoMovimiento());
		movimiento.setIdCuenta(movimientoDeCuentasDto.getIdCuenta());

		List<Movimiento> movimientos = movimientoServicio
				.buscarMovimientosCuenta(movimientoDeCuentasDto.getIdCuenta());

		movimiento.setSaldo(obtenerSaldoActual(movimientos, movimientoDeCuentasDto.getIdCuenta()));

		movimiento.setValor(obtenerValorPorTipoMovimiento(movimientoDeCuentasDto.getTipoMovimiento(),
				movimientoDeCuentasDto.getValor()));

		movimiento.setSaldo(movimiento.getSaldo().add(movimiento.getValor()));

		if (movimiento.getSaldo().compareTo(BigDecimal.ZERO) <= 0
				&& movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.R)) {
			throw new MovimientosException("Saldo no disponible");
		}

		if (movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.R)
				&& validarLimiteDiarioRetiro(movimientos, movimientoDeCuentasDto.getValor())) {
			log.error("Cupo Diario Excedido");
			throw new MovimientosException("Cupo Diario Excedido");
		}

		movimientoServicio.registrar(movimiento);

		return movimiento;
	}

	private BigDecimal obtenerValorPorTipoMovimiento(TipoMovimientoEnum tipoMovimiento, BigDecimal valor) {
		return tipoMovimiento.equals(TipoMovimientoEnum.R) ? valor.multiply(BigDecimal.valueOf(-1.0)) : valor;
	}

	private BigDecimal obtenerSaldoActual(List<Movimiento> movimientos, Long numeroCuenta) {
		BigDecimal saldoActual = BigDecimal.ZERO;
		if (movimientos.isEmpty()) {
			Cuenta cuenta = cuentaServicio.listarPorId(numeroCuenta);
			saldoActual = cuenta.getSaldoInicial();
		} else {
			Optional<Movimiento> ultimoMovimiento = movimientos.stream()
					.max(Comparator.comparing(Movimiento::getFecha));
			if (ultimoMovimiento.isPresent()) {
				saldoActual = ultimoMovimiento.get().getSaldo();
			}
		}
		return saldoActual;
	}

	private boolean validarLimiteDiarioRetiro(List<Movimiento> movimientos, BigDecimal valorRetiro) {
		BigDecimal valorMovimientosDeHoy = movimientos.stream()
				.filter(m -> m.getFecha().toLocalDate().equals(LocalDate.now())
						&& m.getTipoMovimiento().equals(TipoMovimientoEnum.R))
				.map(val -> val.getValor().multiply(BigDecimal.valueOf(-1.0))).reduce(BigDecimal.ZERO, BigDecimal::add);
		return valorMovimientosDeHoy.add(valorRetiro).compareTo(LIMITE_DIARIO_RETIRO) > 0;

	}*/

}
