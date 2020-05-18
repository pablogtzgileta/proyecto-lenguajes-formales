public class Main {

    public static void main(String[] args) {
        Modulo1 modulo1 = new Modulo1();
        // 3 ejemplos
//        System.out.println(modulo1.exToPostix("(F,f)&&&"));
//        System.out.println(modulo1.exToPostix("(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z)+in(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z) *( )"));
//        System.out.println(new Modulo1().exToPostix("&ac,(E,e)x(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z)*( )"));

        Modulo2 modulo2 = new Modulo2();
        String postfijo = modulo1.exToPostix("(F,f)&&&");
        modulo2.afne(postfijo);
    }
}


//        System.out.println(pattern);
//        System.out.println(pattern.get(0));
//        System.out.println(pattern.get(1).get('F')[0]);
//        System.out.println(pattern.get(3));
//        System.out.println(stack);
//        System.out.println(initialGlobalState);
