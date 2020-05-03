import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.map.IMap;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map.Entry;

public class HExecuteOnEntries {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

		IMap<Long, LigaSportowa> ligaSportowa = client.getMap("ligaSportowa");
		ligaSportowa.executeOnEntries(new HEntryProcessor());

		for (Entry<Long, LigaSportowa> e : ligaSportowa.entrySet()) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
	}
}

class HEntryProcessor implements EntryProcessor<Long, LigaSportowa, String>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(Entry<Long, LigaSportowa> e) {
		LigaSportowa ligaSportowa = e.getValue();
		String name = ligaSportowa.getNameDyscypline();
		if (name.equals(name.toLowerCase())) {
			name = name.toUpperCase();
			ligaSportowa.setNameDyscypline(name);
		} else{
			name = name.toLowerCase();
			ligaSportowa.setNameDyscypline(name);
		}
		
		System.out.println("Processing = " + ligaSportowa);
		e.setValue(ligaSportowa);
		
		return name;
	}
}
