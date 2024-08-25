/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jndrepdns;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
////import java.util.Hashtable;
//import java.util.Hashtable;
//import javax.naming.Context;
//import javax.naming.NamingEnumeration;
//import javax.naming.directory.Attributes;
//import javax.naming.directory.DirContext;
//import javax.naming.directory.InitialDirContext;
//import org.xbill.DNS.*;




















import org.xbill.DNS.*;
/**
 *
 * @author Bashar Aljneed
 */
public class JndRepDns {

    /**
//     * @param args the command line arguments
     */

//public class DNSLookup {
    
    public static void main(String[] args) {

if (args.length == 0) {
            System.out.println("Error: No domain name provided.");
            System.out.println("Usage: java DNSLookup <domain name>");
            return;
        }

        String domainName = args[0];

        try {
            Resolver resolver = new SimpleResolver("8.8.8.8"); // استخدام خادم Google DNS على سبيل المثال
            resolver.setTimeout(5); // تحديد مهلة 5 ثوانٍ للاستعلام

            // البحث عن سجلات DNS
            Lookup lookup = new Lookup(domainName, Type.ANY);
            lookup.setResolver(resolver);
            lookup.setCache(null); // تجنب استخدام الكاش
             org.xbill.DNS.Record[] records = lookup.run();
            
             
            if (lookup.getResult() == Lookup.SUCCESSFUL) {
                // جلب تفاصيل السجلات
                                boolean isAuthoritative = lookup.getResult() == Lookup.SUCCESSFUL && lookup.getAnswers().length > 0;

                System.out.println("Domain Name: " + domainName);
             
                
                           
                
                
                
                
                
                // التعامل مع سجلات CNAME (Aliases)
                Lookup cnameLookup = new Lookup(domainName, Type.CNAME);
                cnameLookup.setCache(null);
                org.xbill.DNS.Record[] cnameRecords = cnameLookup.run();
                if (cnameLookup.getResult() == Lookup.SUCCESSFUL) {
                    System.out.println("Aliases:");
                    for (org.xbill.DNS.Record record : cnameRecords) {
                        CNAMERecord cnameRecord = (CNAMERecord) record;
                        System.out.println("  Alias: " + cnameRecord.getName() + " -> " + cnameRecord.getTarget());
                    }
                }

                // التعامل مع سجلات A و AAAA (Addresses)
                Lookup addressLookup = new Lookup(domainName, Type.A);
                addressLookup.setCache(null);
                org.xbill.DNS.Record[] addressRecords = addressLookup.run();
                if (addressLookup.getResult() == Lookup.SUCCESSFUL) {
                    System.out.println("Addresses:");
                    for (org.xbill.DNS.Record record : addressRecords) {
                        ARecord aRecord = (ARecord) record;
                        System.out.println("  Address: " + aRecord.getAddress());
                    }
                }

                
                
                
                
                Lookup ipv6Lookup = new Lookup(domainName, Type.AAAA);
                ipv6Lookup.setCache(null);
                org.xbill.DNS.Record[] ipv6Records = ipv6Lookup.run();
                if (ipv6Lookup.getResult() == Lookup.SUCCESSFUL) {
                    for (org.xbill.DNS.Record record : ipv6Records) {
                        AAAARecord aaaaRecord = (AAAARecord) record;
                        System.out.println("  Address (IPv6): " + aaaaRecord.getAddress());
                    }
                }

                if (isAuthoritative) {
                    System.out.println("Authoritative answer:");
                } else {
                    System.out.println("Non-authoritative answer:");
                }

                
                // التعامل مع سجلات أخرى
                System.out.println("Records:");
                for (org.xbill.DNS.Record record : records) {
                    System.out.println("Record Name: " + record.getName());
                    System.out.println("Record Type: " + Type.string(record.getType()));
                    System.out.println("TTL: " + record.getTTL());
                    System.out.println("Record Data: " + record.toString());
                    System.out.println();




                }
            } else {
                System.out.println("DNS lookup failed: " + lookup.getErrorString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







        // التحقق من وجود اسم النطاق كمدخل
//        if (args.length != 1) {
//            System.out.println("Usage: java DNSLookup <domain name>");
//            return;
//        }



        // الحصول على اسم النطاق من المدخلات
//        String domainName = args[0];
//        
//        try {
//            // الحصول على جميع عناوين IP المرتبطة باسم النطاق
//            InetAddress[] addresses = InetAddress.getAllByName(domainName);
//            
//            System.out.println("Domain Name: " + domainName);
//            
//            // عرض عناوين IP
//            for (InetAddress address : addresses) {
//                System.out.println("Address: " + address.getHostAddress());
//            }
//
//            // إعداد البيئة لإجراء استعلام DNS
//            Hashtable<String, String> env = new Hashtable<>();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
////            DirContext ctx = new InitialDirContext(env);
//            
////            Hashtable<String, String> env = new Hashtable<>();
//env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
//env.put(Context.PROVIDER_URL, "dns://8.8.8.8");  // خادم DNS الخاص بجوجل
//DirContext ctx = new InitialDirContext(env);
//
//
//            // استعلام عن سجلات DNS المختلفة
//            Attributes attrs = ctx.getAttributes(domainName, new String[]{"A", "AAAA", "CNAME", "NS", "SOA", "MX", "TXT"});
//            
//            // عرض السجلات التي تم الحصول عليها
//            NamingEnumeration<?> e = attrs.getAll();
//            while (e.hasMore()) {
//                System.out.println(e.next());
//            }
//
//        } catch (UnknownHostException e) {
//            System.out.println("Unknown host: " + domainName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }







//
//    public static void main(String[] args) {
//        // TODO code application logic here
//                System.out.println("Hello World!");
//
//    }
    
//}
