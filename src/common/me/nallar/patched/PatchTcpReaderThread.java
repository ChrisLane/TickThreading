package me.nallar.patched;

import net.minecraft.network.TcpConnection;
import net.minecraft.server.ThreadMinecraftServer;
import net.minecraft.util.AxisAlignedBB;

public abstract class PatchTcpReaderThread extends ThreadMinecraftServer {
	int count;
	final TcpConnection tcpConnection;

	public PatchTcpReaderThread(TcpConnection tcpConnection, String name) {
		super(null, name);
		this.tcpConnection = tcpConnection;
	}

	@Override
	public void run() {
		TcpConnection.field_74471_a.getAndIncrement();

		try {
			while (tcpConnection.isRunning()) {
				while (true) {
					if (!tcpConnection.readNetworkPacket()) {
						try {
							sleep(2L);
						} catch (InterruptedException ignored) {
						}
					}
					if (count++ % 30 == 0) {
						AxisAlignedBB.getAABBPool().cleanPool();
					}
				}
			}
		} finally {
			TcpConnection.field_74471_a.getAndDecrement();
		}
	}
}