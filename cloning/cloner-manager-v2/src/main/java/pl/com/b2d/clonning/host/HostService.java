package pl.com.b2d.clonning.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Łukasz Kucharski on 2016-12-20.
 */
@Service
@Transactional
public class HostService {

    private static final Pattern PATTERN = Pattern.compile("([a-z0-9]+)\\.(.*)");

    private final HostRepository hostRepository;

    @Autowired
    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> listHosts() {
        return hostRepository.findAll();
    }

    public Host getHost(String hostDomain) {
        Matcher matcher = PATTERN.matcher(hostDomain);
        if (matcher.find()) {
            return getHost(new HostDomain(matcher.group(1), matcher.group(2)));
        }
        throw new RuntimeException("Format of HostDomain is incorrect: " + hostDomain);
    }

    public Host getHost(final HostDomain hostDomain) {
        Host host = hostRepository.findByHostDomain(hostDomain);
        if (host == null) {
            throw new RuntimeException("Not found Host by hostDomain: " + hostDomain);
        }
        return host;
    }

    public Host save(final Host host) {
        return hostRepository.save(host);
    }
}
