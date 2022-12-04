const int STR_BUF_SIZE = 256;
const int INT_BUF_SIZE = 20;

char* __malloc(int size) { return malloc(size); }
char* __str_plus(char* s1, char* s2) {
    char* s = malloc(strlen(s1) + strlen(s2) + 1);
    strcpy(s, s1);
    strcat(s, s2);
    return s;
}
char __str_eq(char* s1, char* s2) { return strcmp(s1, s2) == 0; }
char __str_ne(char* s1, char* s2) { return strcmp(s1, s2) != 0; }
char __str_lt(char* s1, char* s2) { return strcmp(s1, s2) < 0; }
char __str_le(char* s1, char* s2) { return strcmp(s1, s2) <= 0; }
char __str_gt(char* s1, char* s2) { return strcmp(s1, s2) > 0; }
char __str_ge(char* s1, char* s2) { return strcmp(s1, s2) >= 0; }
int __str_length(char* s) { return strlen(s); }
char* __str_substring(char* s, int start, int end) {
    char* sub = malloc(end - start + 1);
    memcpy(sub, s + start, end - start);
    sub[end - start] = '\0';
    return sub;
}
int __str_parseInt(char* s) { 
    int i;
    sscanf(s, "%d", &i);
    return i;
}
int __str_ord(char* s, int i) { return s[i]; }

void print(char* str) { printf("%s", str); }
void println(char* str) { printf("%s\n", str); }
void printInt(int i) { printf("%d", i); }
void printlnInt(int i) { printf("%d\n", i); }

char* getString() {
    char* buf = (char*)malloc(256);
    scanf("%s", buf);
    return buf;
}
int getInt() {
    int i;
    scanf("%d", &i);
    return i;
}
char* toString(int i) {
    char* buf = (char*)malloc(12);
    sprintf(buf, "%d", i);
    return buf;
}